package com.bs.init;

import java.sql.Connection;

public class InitialParams {
	private Connection conn;
	private boolean blockedIP;
	private int attemptLeft;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public boolean isBlockedIP() {
		return blockedIP;
	}

	public void setBlockedIP(boolean blockedIP) {
		this.blockedIP = blockedIP;
	}

	public int getAttemptLeft() {
		return attemptLeft;
	}

	public void setAttemptLeft(int attemptLeft) {
		this.attemptLeft = attemptLeft;
	}
}
