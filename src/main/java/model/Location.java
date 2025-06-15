package model;

public class Location {
    private String city;
    private String country;
    private int id;
    private String token;

    public Location() {
    }

    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }public String getToken() {
        return token;
    }public void setToken(String token) {
        this.token = token;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public boolean checkCountryLength(String country) {
        return (country.length() <= 60);
    }
    public boolean checkCityLength(String city) {
        return (city.length() <= 60);
    }
}
