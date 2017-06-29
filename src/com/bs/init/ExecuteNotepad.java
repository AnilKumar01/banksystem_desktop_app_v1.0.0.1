package com.bs.init;

import java.io.IOException;

public class ExecuteNotepad {
	public ExecuteNotepad(String fileName){
		Runtime rt=Runtime.getRuntime();
		String cmd="notepad "+fileName;
		try {
			rt.exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
