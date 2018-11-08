package com.fedex.services.jmstool.mock;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.jms.BytesMessage;
import javax.jms.Destination;

import org.apache.commons.collections.iterators.IteratorEnumeration;

public class BytesMessageMock implements BytesMessage {
	private Map<String, Object> properties = new HashMap<>();

	@Override
	public long getBodyLength() {
		return 0;
	}

	@Override
	public boolean readBoolean() {
		return false;
	}

	@Override
	public byte readByte() {
		return 0;
	}

	@Override
	public int readUnsignedByte() {
		return 0;
	}

	@Override
	public short readShort() {
		return 0;
	}

	@Override
	public int readUnsignedShort() {
		return 0;
	}

	@Override
	public char readChar() {
		return 0;
	}

	@Override
	public int readInt() {
		return 0;
	}

	@Override
	public long readLong() {
		return 0;
	}

	@Override
	public float readFloat() {
		return 0;
	}

	@Override
	public double readDouble() {
		return 0;
	}

	@Override
	public String readUTF() {
		return null;
	}

	@Override
	public int readBytes(byte[] bytes) {
		return 0;
	}

	@Override
	public int readBytes(byte[] bytes, int i) {
		return 0;
	}

	@Override
	public void writeBoolean(boolean b) {

	}

	@Override
	public void writeByte(byte b) {

	}

	@Override
	public void writeShort(short i) {

	}

	@Override
	public void writeChar(char c) {

	}

	@Override
	public void writeInt(int i) {

	}

	@Override
	public void writeLong(long l) {

	}

	@Override
	public void writeFloat(float v) {

	}

	@Override
	public void writeDouble(double v) {

	}

	@Override
	public void writeUTF(String s) {

	}

	@Override
	public void writeBytes(byte[] bytes) {

	}

	@Override
	public void writeBytes(byte[] bytes, int i, int i1) {

	}

	@Override
	public void writeObject(Object o) {

	}

	@Override
	public void reset() {

	}

	@Override
	public String getJMSMessageID() {
		return null;
	}

	@Override
	public void setJMSMessageID(String s) {

	}

	@Override
	public long getJMSTimestamp() {
		return 0;
	}

	@Override
	public void setJMSTimestamp(long l) {

	}

	@Override
	public byte[] getJMSCorrelationIDAsBytes() {
		return new byte[0];
	}

	@Override
	public void setJMSCorrelationIDAsBytes(byte[] bytes) {

	}

	@Override
	public void setJMSCorrelationID(String s) {

	}

	@Override
	public String getJMSCorrelationID() {
		return null;
	}

	@Override
	public Destination getJMSReplyTo() {
		return null;
	}

	@Override
	public void setJMSReplyTo(Destination destination) {

	}

	@Override
	public Destination getJMSDestination() {
		return null;
	}

	@Override
	public void setJMSDestination(Destination destination) {

	}

	@Override
	public int getJMSDeliveryMode() {
		return 0;
	}

	@Override
	public void setJMSDeliveryMode(int i) {

	}

	@Override
	public boolean getJMSRedelivered() {
		return false;
	}

	@Override
	public void setJMSRedelivered(boolean b) {

	}

	@Override
	public String getJMSType() {
		return null;
	}

	@Override
	public void setJMSType(String s) {

	}

	@Override
	public long getJMSExpiration() {
		return 0;
	}

	@Override
	public void setJMSExpiration(long l) {

	}

	@Override
	public long getJMSDeliveryTime() {
		return 0;
	}

	@Override
	public void setJMSDeliveryTime(long l) {

	}

	@Override
	public int getJMSPriority() {
		return 0;
	}

	@Override
	public void setJMSPriority(int i) {

	}

	@Override
	public void clearProperties() {

	}

	@Override
	public boolean propertyExists(String s) {
		return false;
	}

	@Override
	public boolean getBooleanProperty(String s) {
		return false;
	}

	@Override
	public byte getByteProperty(String s) {
		return 0;
	}

	@Override
	public short getShortProperty(String s) {
		return 0;
	}

	@Override
	public int getIntProperty(String s) {
		return 0;
	}

	@Override
	public long getLongProperty(String s) {
		return 0;
	}

	@Override
	public float getFloatProperty(String s) {
		return 0;
	}

	@Override
	public double getDoubleProperty(String s) {
		return 0;
	}

	@Override
	public String getStringProperty(String s) {
		return null;
	}

	@Override
	public Object getObjectProperty(String s) {
		return null;
	}

	@Override
	public Enumeration getPropertyNames() {
		return new IteratorEnumeration(properties.keySet().iterator());
	}

	@Override
	public void setBooleanProperty(String s, boolean b) {
		properties.put(s, b);
	}

	@Override
	public void setByteProperty(String s, byte b) {
		properties.put(s, b);
	}

	@Override
	public void setShortProperty(String s, short i) {
		properties.put(s, i);
	}

	@Override
	public void setIntProperty(String s, int i) {
		properties.put(s, i);
	}

	@Override
	public void setLongProperty(String s, long l) {
		properties.put(s, l);
	}

	@Override
	public void setFloatProperty(String s, float v) {
		properties.put(s, v);
	}

	@Override
	public void setDoubleProperty(String s, double v) {
		properties.put(s, v);
	}

	@Override
	public void setStringProperty(String s, String s1) {
		properties.put(s, s1);
	}

	@Override
	public void setObjectProperty(String s, Object o) {
		properties.put(s, o);
	}

	@Override
	public void acknowledge() {

	}

	@Override
	public void clearBody() {

	}

	@Override
	public <T> T getBody(Class<T> aClass) {
		return null;
	}

	@Override
	public boolean isBodyAssignableTo(Class aClass) {
		return false;
	}
}
