package com.fidesmo.javacard.counter;

import javacard.framework.*;

public class CounterApplet extends Applet
{
    final static short noOffset = (short) 0;
    final static short counterLength = (short) 1;

    //final byte[] value;
    protected byte value;
 
    // Stores the counter value received as installation parameter
    protected CounterApplet(byte[] bArray, short bOffset, byte bLength){
        // names in accordance with java card documentation
        short li = bArray[bOffset];
        short lc = bArray[(short)(bOffset + li + 1)];
        short offset = (short)(li + lc + 3);
        byte length = bArray[(short)(offset - 1)];
        if (length != 1) {
            ISOException.throwIt(ISO7816.SW_CONDITIONS_NOT_SATISFIED);
        }
        value = bArray[(short)offset];
        register();
    }

    /**
     * Installs this applet.
     * @param bArray the array containing installation parameters
     * @param bOffset the starting offset in bArray
     * @param bLength the length in bytes of the parameter data in bArray
     */
    public static void install(byte[] bArray, short bOffset, byte bLength){
        new CounterApplet(bArray, bOffset, bLength);
    }

    /**
     * Processes an incoming APDU. Implements two operations:
     *  - SELECT to return counter value
     *  - DECREMENT to decrease counter and return new value
     * @see APDU
     * @param apdu the incoming APDU
     * @exception ISOException with the response bytes per ISO 7816-4
     */
    public void process(APDU apdu){

        apdu.setOutgoing();

        byte buffer[] = apdu.getBuffer();

        switch (buffer[ISO7816.OFFSET_INS]) {
        // SELECT instruction
        case ISO7816.INS_SELECT:
            apdu.setOutgoingLength(counterLength);
            buffer[noOffset] = value;
            apdu.sendBytes(noOffset, counterLength);
            break;
        // DECREMENT instruction
        case (byte)0: 
            if (value > 0) {
                // decrease value and return new value
                value = (byte)(value - 1);
                apdu.setOutgoingLength(counterLength);
                buffer[noOffset] = value;
                apdu.sendBytes(noOffset, counterLength);
            } else {
                // if counter cannot be decremented
                ISOException.throwIt(ISO7816.SW_CONDITIONS_NOT_SATISFIED);
            }
            break;
        default:
            ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
        }
                     
    }
}
