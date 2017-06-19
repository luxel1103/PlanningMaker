/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontyspublisher;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Component test for RemotePublisher. Remote publisherForDomain is accessed
 * through IRemotePublisherForDomain and IRemotePublisherForListener
 * Start remote publisherForDomain by running RemotePublisherTestServer.java
 * 
 * @author Nico Kuijpers
 */
public class RemotePublisherComponentTest {

    // Remote publisher
    static private IRemotePublisherForDomain publisherForDomain;
    static private IRemotePublisherForListener publisherForListener;
    private static int portNumber = 1099;
    private static String bindingName = "publisher";
    
    // Properties to be published
    private String[] properties;

    // Note that listeners are informed asynchronously
    // Define delay to ensure that listeners have been informed
    private final int delay = 10; // 10 ms

    public RemotePublisherComponentTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        // Establish connection with remote publisherForDomain
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", portNumber);
            publisherForDomain = (IRemotePublisherForDomain) registry.lookup(bindingName);
            publisherForListener = (IRemotePublisherForListener) registry.lookup(bindingName);
            System.out.println("Connection with remote publisher established");
        } catch (RemoteException | NotBoundException re) {
            System.err.println("Cannot establish connection to remote publisher");
            System.err.println("Run RemotePublisherTestServer before running component test");
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws RemoteException {
        // Initialize remote publisher by registering properties
        properties = new String[]{"propA","propB"};
        for (String property : properties) {
            publisherForDomain.registerProperty(property);
        }
    }

    @After
    public void tearDown() throws RemoteException {
        // Reset remote publisher by unregistering all properties
        publisherForDomain.unregisterProperty(null);
    }

    // Note that listeners are informed asynchronously
    // Wait some time to ensure that listeners have been informed
    private void wait(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(PublisherTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSubscribeRemoteListenerSuccessful() throws RemoteException {
        /**
         * Subscribe remote property listener. Remote listener will be
         * subscribed to given property. In case given property is the
         * null-String, the listener will be subscribed to all properties.
         *
         * @param listener property listener to be subscribed
         * @param property null-String allowed
         */

        // Subscribe remote property listener to propB 
        StubRemotePropertyListener listener = new StubRemotePropertyListener();
        publisherForListener.subscribeRemoteListener(listener, "propB");

        // Listener should not have received any events yet
        List<PropertyChangeEvent> receivedEvents = listener.getReceivedEvents();
        int expectedNrEvents = 0;
        int actualNrEvents = receivedEvents.size();
        Assert.assertEquals("number of received events before inform", expectedNrEvents, actualNrEvents);

        // Inform subscribed listeners about propB
        publisherForDomain.inform("propB", "propBold", "propBnew");

        // Wait some time to ensure that all listeners have been informed
        wait(delay);

        // Listener should have received exactly one event
        receivedEvents = listener.getReceivedEvents();
        expectedNrEvents = 1;
        actualNrEvents = receivedEvents.size();
        Assert.assertEquals("number of received events after inform", expectedNrEvents, actualNrEvents);

        // Check old value of received event
        PropertyChangeEvent event = receivedEvents.get(0);
        String expectedString = "propBold";
        String actualString = (String) event.getOldValue();
        Assert.assertEquals("old value of event", expectedString, actualString);

        // Check new value of received event
        expectedString = "propBnew";
        actualString = (String) event.getNewValue();
        Assert.assertEquals("new value of event", expectedString, actualString);
    }

    @Test
    public void testUnsubscribeRemoteListenerSuccessful() throws RemoteException {
        /**
         * Unsubscribe remote property listener. Listener will be unsubscribed
         * from given property. In case given property is the null-string, the
         * listener will be unsubscribed from all properties.
         *
         * @param listener property listener to be unsubscribed
         * @param property null-String allowed
         */

        // Subscribe remote property listener to propB 
        StubRemotePropertyListener listener = new StubRemotePropertyListener();
        publisherForListener.subscribeRemoteListener(listener, "propB");

        // Unsubscribe listener from propA
        publisherForListener.unsubscribeRemoteListener(listener, "propB");

        // Inform subscribed listeners about propA
        publisherForDomain.inform("propB", "propBold", "propBnew");

        // Wait some time to ensure that all listeners have been informed
        wait(delay);

        // Listener should not have received any events 
        List<PropertyChangeEvent> receivedEvents = listener.getReceivedEvents();
        int expectedNrEvents = 0;
        int actualNrEvents = receivedEvents.size();
        Assert.assertEquals("number of received events after inform", expectedNrEvents, actualNrEvents);
    }

    @Test
    public void testInformSuccessful() throws RemoteException {
        /**
         * Inform all listeners subscribed to property. All listeners subscribed
         * to given property as well as all listeners subscribed to null-String
         * are informed of a change of given property through a (remote) method
         * invocation of propertyChange(). In case given property is the
         * null-String all subscribed listeners are informed.
         *
         * @param property property is either null-String or is registered
         * @param oldValue original value of property at domain (null is
         * allowed)
         * @param newValue new value of property at domain
         */

        // Subscribe remote property listener to propA 
        StubRemotePropertyListener remoteListenerPropA = new StubRemotePropertyListener();
        publisherForListener.subscribeRemoteListener(remoteListenerPropA, "propA");

        // Subscribe remote property listener to null-String 
        StubRemotePropertyListener remoteListenerAllProps = new StubRemotePropertyListener();
        publisherForListener.subscribeRemoteListener(remoteListenerAllProps, null);

        // Inform subscribed listeners about propA
        publisherForDomain.inform("propA", "propAold", "propAnew");

        // Inform subscribed listeners about propB
        publisherForDomain.inform("propB", "propBold", "propBnew");

        // Wait some time to ensure that all listeners have been informed
        wait(delay);

        // Remote listener to propA should have received exactly one event
        List<PropertyChangeEvent> receivedEvents = remoteListenerPropA.getReceivedEvents();
        int expectedNrEvents = 1;
        int actualNrEvents = receivedEvents.size();
        Assert.assertEquals("number of received events by remote listener to propA",
                expectedNrEvents, actualNrEvents);

        // Remote listener to all properties should have received exactly two events
        receivedEvents = remoteListenerAllProps.getReceivedEvents();
        expectedNrEvents = 2;
        actualNrEvents = receivedEvents.size();
        Assert.assertEquals("number of received events by remote listener to all properties",
                expectedNrEvents, actualNrEvents);
    }

    @Test
    public void testGetProperties() throws RemoteException {
        /**
         * Obtain all registered properties. An unmodifiable list all properties
         * including the null property is returned.
         *
         * @return list of registered properties including null
         */

        // Properties propA and propB are already registered
        // List of properties should contain propA, propB, and null
        List<String> registeredProperties = publisherForDomain.getProperties();
        int expectedNrRegistered = 3;
        int actualNrRegistered = registeredProperties.size();
        Assert.assertEquals("number of registered properties", expectedNrRegistered, actualNrRegistered);
        Assert.assertTrue("property propA not registered", registeredProperties.contains("propA"));
        Assert.assertTrue("property propB not registered", registeredProperties.contains("propB"));
        Assert.assertTrue("property null not registered", registeredProperties.contains(null));
    }

    @Test
    public void testRegisterPropertySuccesful() throws RemoteException {
        /**
         * Register property. Register property at this publisher. From now on
         * listeners can subscribe to this property. Nothing changes in case
         * given property was already registered.
         *
         * @param property empty string not allowed
         */

        // Register property propC
        publisherForDomain.registerProperty("propC");

        // Properties propA and propB are already registered
        // List of properties should contain propA, propB, propC, and null
        List<String> registeredProperties = publisherForDomain.getProperties();
        int expectedNrRegistered = 4;
        int actualNrRegistered = registeredProperties.size();
        Assert.assertEquals("number of registered properties", expectedNrRegistered, actualNrRegistered);
        Assert.assertTrue("property propA not registered", registeredProperties.contains("propA"));
        Assert.assertTrue("property propB not registered", registeredProperties.contains("propA"));
        Assert.assertTrue("property propC not registered", registeredProperties.contains("propC"));
        Assert.assertTrue("property null not registered", registeredProperties.contains(null));
    }

    @Test
    public void testUnregisterPropertySuccesful() throws RemoteException {
        /**
         * Unregister property. Unregister property at this publisher. From now
         * on listeners subscribed to this property will not be informed on
         * changes. In case given property is null-String, all properties
         * (except null) will be unregistered.
         *
         * @param property registered property at this publisher
         */

        // Register property propC
        publisherForDomain.registerProperty("propC");

        // Unregister property propC
        publisherForDomain.unregisterProperty("propC");

        // Properties propA and propB are already registered
        // List of properties should contain propA, propB, and null, but not propC
        List<String> registeredProperties = publisherForDomain.getProperties();
        int expectedNrRegistered = 3;
        int actualNrRegistered = registeredProperties.size();
        Assert.assertEquals("number of registered properties", expectedNrRegistered, actualNrRegistered);
        Assert.assertTrue("property propA not registered", registeredProperties.contains("propA"));
        Assert.assertTrue("property propB not registered", registeredProperties.contains("propB"));
        Assert.assertTrue("property null not registered", registeredProperties.contains(null));
        Assert.assertFalse("property propC registered", registeredProperties.contains("propC"));
    }
}
