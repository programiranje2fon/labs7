package task1;

public class Vehicle {

	private String regNum;

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vehicle)) {
			return false;
		}
		
		Vehicle v1 = (Vehicle) obj;
		
		return this.regNum.equals(v1.regNum);
	}

}
