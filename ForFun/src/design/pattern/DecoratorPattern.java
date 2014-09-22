package design.pattern;


public class DecoratorPattern {
	public static void main(String[] args) {
		IceCream iceCream = new NuttyDecorator(new HoneyDecorator(
				new SimpleIceCream()));
		System.out.println(iceCream.makeIceCream());
	}
}

class SimpleIceCream implements IceCream {

	@Override
	public String makeIceCream() {
		return "Base IceCream";
	}
}

abstract class IceCreamDecorator implements IceCream {
	protected IceCream specialIceCream;

	public IceCreamDecorator(IceCream iceCream) {
		this.specialIceCream = iceCream;
	}

	@Override
	public String makeIceCream() {
		return this.specialIceCream.makeIceCream();
	}
}

class NuttyDecorator extends IceCreamDecorator {
	public NuttyDecorator(IceCream iceCream) {
		super(iceCream);
	}

	public String makeIceCream() {
		return specialIceCream.makeIceCream() + addNut();
	}

	private String addNut() {
		return " + cruncy nuts";
	}
}

class HoneyDecorator extends IceCreamDecorator {
	public HoneyDecorator(IceCream iceCream) {
		super(iceCream);
	}

	public String makeIceCream() {
		return specialIceCream.makeIceCream() + addHoney();
	}

	private String addHoney() {
		return " + sweet honey";
	}
}