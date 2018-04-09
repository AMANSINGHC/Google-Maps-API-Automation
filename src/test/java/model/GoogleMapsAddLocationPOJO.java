package model;

public class GoogleMapsAddLocationPOJO {

	public Location location=new Location();
	
	public class Location {
		
		public double lat;
		public double lng;
	}
	
	public int accuracy;
	public String name;
	public String phone_number;
	public String address;
	public String types[];
	public String website;
	public String language;
}
