package neustadt.microprocessor;

public class Microprocessor {

	private char accumulatorA;
	private char accumulatorB;
	private Memory memory;

	public Microprocessor(String memoryString) {
		this.accumulatorA = '0';
		this.accumulatorB = '0';
		this.memory = new Memory(memoryString);

		processMemory();
	}

	private void processMemory() {
		int index = 0;
		char code;

		while ((code = memory.getChar(index)) != '8') {
			index++;

			switch (code) {
			case '0':
				int location = memory.getAddress(index);
				accumulatorA = memory.getChar(location);
				index += 2;
				break;

			case '1':
				location = memory.getAddress(index);
				memory.setChar(location, accumulatorA);
				index += 2;
				break;

			case '2':
				char temp = accumulatorA;
				accumulatorA = accumulatorB;
				accumulatorB = temp;
				break;

			case '3':
				sum();
				break;

			case '4':
				increment();
				break;

			case '5':
				decrement();
				break;

			case '6':
				if (accumulatorA == '0') {
					index = memory.getAddress(index);
				} else {
					index += 2;
				}
				break;

			case '7':
				index = memory.getAddress(index);
				break;
			}
		}
		System.out.println(memory);
	}

	private void sum() {
		int sum = Integer.parseInt(String.valueOf(accumulatorA), 16)
				+ Integer.parseInt(String.valueOf(accumulatorB), 16);
		String hex = Integer.toString(sum, 16).toUpperCase();
		if (hex.length() == 1) {
			accumulatorB = '0';
			accumulatorA = hex.charAt(0);
		} else {
			accumulatorB = hex.charAt(0);
			accumulatorA = hex.charAt(1);
		}
	}

	private void increment() {
		if (accumulatorA == 'F') {
			accumulatorA = '0';
		} else {
			int temp1 = Integer.parseInt(String.valueOf(accumulatorA), 16);
			temp1++;
			String hex1 = Integer.toString(temp1, 16).toUpperCase();
			accumulatorA = hex1.charAt(0);
		}
	}

	private void decrement() {
		if (accumulatorA == '0') {
			accumulatorA = 'F';
		} else {
			int temp2 = Integer.parseInt(String.valueOf(accumulatorA), 16);
			temp2--;
			String hex2 = Integer.toString(temp2, 16).toUpperCase();
			accumulatorA = hex2.charAt(0);
		}
	}

}