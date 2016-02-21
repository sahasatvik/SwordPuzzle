class Puzzle {

	private int swordCarrier;
	private boolean[] people;
	private int size;

	Puzzle (int size) {
		this.size = size;
		swordCarrier = 1;			// number of the person carrying the sword
		people 	= new boolean[size + 1];	// array of people
		people[0] = false; 			// unrequired person
		for (int i = 1; i <= size; i++) {
			people[i] = true;
		}
	}

	int nextPersonAlive (int n) {
		return (n == size)? (nextPersonAlive(0)) : (people[n+1])? (n+1) : (nextPersonAlive((n+1)));
	}

	void kill (int n) {
		people[n] = false;
	}

	void killPersonAfterSwordCarrier () {
		kill(nextPersonAlive(swordCarrier));
	}

	void passSword () {
		swordCarrier = nextPersonAlive(swordCarrier);
	}

	int peopleAlive () {
		int c = 0;
		for (int i = 1; i <= size; i++) {
			c += (people[i])? 1 : 0;
		}
		return c;
	}

	void display () {
		for (int i = 1; i <= size; i++) {
			System.out.print(((people[i])? i : "X") + "\t");
			System.out.print((i%10 == 0)? "\n" : "");
		}
		System.out.print("\n");
	}

	public static void main (String args[]) {
		int size = (args.length == 2 || args.length == 1)? Integer.parseInt(args[0]) : 100;
		int alive = (args.length == 2)? Integer.parseInt(args[1]) : 1;
		Puzzle p = new Puzzle(size);
		
		while (p.peopleAlive() > alive) { 	// loop through people
			p.killPersonAfterSwordCarrier();
			p.passSword();
		}
		p.display();
	}
}
