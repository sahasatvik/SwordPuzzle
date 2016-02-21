/*
 *  Program to calculate and display the solution of the sword puzzle, as mentioned in README.md
 *
 *  Usage :
 *  	
 *  	java Puzzle [number of people] [number of people to be kept alive]
 *
 *  
 *  The program will print the number of the last person standing, unless more than one person is kept alive.
 *  In that case, a rectangular array of numbers will be displayed, where :
 *
 *  	-number-	->	dead person
 *  	 number		->	alive person
 *  	[number]	->	person carrying the swordCarrier
 *
 */

class Puzzle {

	private int swordCarrier;
	private boolean[] people;
	private int numberOfPeople;

	Puzzle (int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;				// copy number of people
		swordCarrier = 1;						// number of the person carrying the sword
		people 	= new boolean[numberOfPeople + 1];			// array of people
		people[0] = false; 						// extra space in array (unrequired)
		for (int i = 1; i <= numberOfPeople; i++) {
			people[i] = true;					// make everyone alive initially
		}
	}

	int nextPersonAlive (int n) {						// return the next person standing after 'n'
		return (n == numberOfPeople)? (nextPersonAlive(0)) : (people[n+1])? (n+1) : (nextPersonAlive((n+1)));
	}

	int getSwordCarrier () {
		return swordCarrier;
	}

	void kill (int n) {
		people[n] = false;
	}

	void killPersonAfterSwordCarrier () {
		kill(nextPersonAlive(swordCarrier));
	}

	void passSword () {
		swordCarrier = nextPersonAlive(swordCarrier);			// pass the sword to the next person standing
	}

	int numberOfPeopleAlive () {						// get the number of people still alive
		int c = 0;
		for (int i = 1; i <= numberOfPeople; i++) {
			c += (people[i])? 1 : 0;
		}
		return c;
	}

	void display () {							// display the array of people
		for (int i = 1; i <= numberOfPeople; i++) {
			System.out.print(((people[i])? ((i == swordCarrier)? ("[" + i + "]") : (" " + i)) : ("-" + i + "-")) + "\t");
			System.out.print((i%10 == 0)? "\n" : "");
		}
		System.out.print("\n");
	}

	public static void main (String args[]) {

		int numberOfPeople = (args.length > 0)? Integer.parseInt(args[0]) : 100;	// get the number of people
		int toBeKeptAlive = (args.length > 1)? Integer.parseInt(args[1]) : 1;		// get the number of people to be left standing

		Puzzle p = new Puzzle(numberOfPeople);

		while (p.numberOfPeopleAlive() > toBeKeptAlive) {		// loop through people
			p.killPersonAfterSwordCarrier();
			p.passSword();
		}

		if (toBeKeptAlive == 1) {
			System.out.print("Person carrying the Sword (last person standing): " + p.getSwordCarrier() + "\n");
		} else {
			p.display();
			System.out.print("\nPerson carrying the Sword : " + p.getSwordCarrier() + "\n");
		}
	}
}
