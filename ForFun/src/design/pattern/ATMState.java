package design.pattern;

public interface ATMState {
	// Different states expected
	// HasCard, NoCard, HasPin, NoCash

	public void InsertCard();

	public void ejectCard();

	public void insertPin(int pin);

	public void requestCash(int cashToWithdraw);
}
