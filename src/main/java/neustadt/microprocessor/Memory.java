package neustadt.microprocessor;

import java.io.BufferedReader;
import java.io.IOException;

public class Memory {

	private char[] memory = new char[256];

	public Memory(BufferedReader reader) throws IOException {
		memory = reader.readLine().toCharArray();
	}

	public Memory(String memoryString) {
		memory = memoryString.toCharArray();
	}

	public int getAddress(int index) {
		StringBuilder builder = new StringBuilder();
		builder.append(memory[index]);
		builder.append(memory[index + 1]);
		int location = Integer.parseInt(builder.toString(), 16);
		return location;
	}

	public char getChar(int index) {
		return memory[index];
	}

	public void setChar(int index, char value) {
		memory[index] = value;
	}

	public String toString() {
		return new String(memory);
	}

}
