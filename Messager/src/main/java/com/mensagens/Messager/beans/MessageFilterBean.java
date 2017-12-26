package com.mensagens.Messager.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

	private @QueryParam("year") int year;
	private @QueryParam("offset") int offset;
	private @QueryParam("range") int range;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

}
