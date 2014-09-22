package design.pattern;

public class StatePattern {
	public static void main(String[] args) {
		ATMMachine atmMachine = new ATMMachine(2000);
		atmMachine.insertCard();
		atmMachine.ejectCard();
		atmMachine.insertCard();
		atmMachine.insertPin(2222);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(1000);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(2000);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(500);
	}
}

class ATMMachine {
	ATMState atmState;

	int cashInMachine;
	boolean correctPinEntered;

	private ATMState hasCard;
	private ATMState noCard;
	private ATMState hasPin;
	private ATMState noCash;

	public ATMMachine(int totalCash) {
		this.cashInMachine = totalCash;
		this.correctPinEntered = false;

		this.hasCard = new HasCard(this);
		this.noCard = new NoCard(this);
		this.hasPin = new HasPin(this);
		this.noCash = new NoCash(this);

		this.atmState = noCard;
	}

	public void insertCard() {
		atmState.InsertCard();
	}

	public void ejectCard() {
		atmState.ejectCard();
	}

	public void requestCash(int cashToWithdraw) {
		atmState.requestCash(cashToWithdraw);
	}

	public void insertPin(int pin) {
		atmState.insertPin(pin);
	}

	public void setCashInMachine(int cash) {
		this.cashInMachine = cash;
	}

	public ATMState getHashCardState() {
		return this.hasCard;
	}

	public ATMState getNoCardState() {
		return this.noCard;
	}

	public ATMState getNoCashState() {
		return this.noCash;
	}

	public ATMState getHasPinState() {
		return this.hasPin;
	}

	public void setATMState(ATMState atmState) {
		this.atmState = atmState;
	}
}

class HasCard implements ATMState {
	ATMMachine atmMachine;

	public HasCard(ATMMachine atmMachine) {
		this.atmMachine = atmMachine;
	}

	@Override
	public void ejectCard() {
		System.out.println("You card is ejected");
		atmMachine.setATMState(atmMachine.getNoCardState());
	}

	@Override
	public void insertPin(int pin) {
		if (pin == 1234) {
			System.out.println("You entered the correct PIN");
			atmMachine.correctPinEntered = true;
			atmMachine.setATMState(atmMachine.getHasPinState());
		} else {
			System.out.println("You entered the incorrect PIN");
			atmMachine.correctPinEntered = false;
			ejectCard();
		}
	}

	@Override
	public void requestCash(int cashToWithdraw) {
		System.out.println("You have not entered your PIN");
	}

	@Override
	public void InsertCard() {
		System.out.println("You can only insert one card at a time");
	}

}

class NoCard implements ATMState {
	ATMMachine atmMachine;

	public NoCard(ATMMachine atmMachine) {
		this.atmMachine = atmMachine;
	}

	@Override
	public void ejectCard() {
		System.out.println("You did not enter the card");
	}

	@Override
	public void insertPin(int pin) {
		System.out.println("You did not enter the card");
	}

	@Override
	public void requestCash(int cashToWithdraw) {
		System.out.println("You did not enter the card");
	}

	@Override
	public void InsertCard() {
		System.out.println("Please enter your card");
		atmMachine.setATMState(atmMachine.getHashCardState());
	}

}

class HasPin implements ATMState {

	ATMMachine atmMachine;

	public HasPin(ATMMachine atmMachine) {
		this.atmMachine = atmMachine;
	}

	@Override
	public void ejectCard() {
		System.out.println("Your card is ejected");
		atmMachine.setATMState(atmMachine.getNoCardState());
	}

	@Override
	public void insertPin(int pin) {
		System.out.println("You already entered a PIN");
	}

	@Override
	public void requestCash(int cashToWithdraw) {
		if (cashToWithdraw > atmMachine.cashInMachine) {
			System.out
					.println("The Machine does not  have that much cash available");
			ejectCard();
		} else {
			System.out.println(cashToWithdraw + " is provided by the machine");
			atmMachine.setCashInMachine(atmMachine.cashInMachine
					- cashToWithdraw);

			ejectCard();

			if (atmMachine.cashInMachine <= 0) {
				atmMachine.setATMState(atmMachine.getNoCashState());
			}
		}
	}

	@Override
	public void InsertCard() {
		System.out.println("You already entered the card");
	}

}

class NoCash implements ATMState {
	ATMMachine atmMachine;

	public NoCash(ATMMachine atmMachine) {
		this.atmMachine = atmMachine;
	}

	@Override
	public void ejectCard() {
		System.out.println("We don't have any money");
		System.out.println("Your card is ejected");
		atmMachine.setATMState(atmMachine.getNoCardState());

	}

	@Override
	public void insertPin(int pin) {
		ejectCard();
	}

	@Override
	public void requestCash(int cashToWithdraw) {
		ejectCard();
	}

	@Override
	public void InsertCard() {
		ejectCard();
	}

}