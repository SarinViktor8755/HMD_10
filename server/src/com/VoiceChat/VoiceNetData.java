package com.VoiceChat;

public class VoiceNetData {

	private short[] data;
	private int id;

	public VoiceNetData(){

	}

	public VoiceNetData(short[] data){
		this.data = data;
	}

	public short[] getData(){
		return data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
