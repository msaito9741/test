package ms.MyBatisSample.entity.jsv;

public class WeatherKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jsv.weather.city_name
     *
     * @mbg.generated Sun Feb 11 13:05:06 JST 2018
     */
    private String cityName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column jsv.weather.weather_no
     *
     * @mbg.generated Sun Feb 11 13:05:06 JST 2018
     */
    private Integer weatherNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jsv.weather.city_name
     *
     * @return the value of jsv.weather.city_name
     *
     * @mbg.generated Sun Feb 11 13:05:06 JST 2018
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jsv.weather.city_name
     *
     * @param cityName the value for jsv.weather.city_name
     *
     * @mbg.generated Sun Feb 11 13:05:06 JST 2018
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column jsv.weather.weather_no
     *
     * @return the value of jsv.weather.weather_no
     *
     * @mbg.generated Sun Feb 11 13:05:06 JST 2018
     */
    public Integer getWeatherNo() {
        return weatherNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column jsv.weather.weather_no
     *
     * @param weatherNo the value for jsv.weather.weather_no
     *
     * @mbg.generated Sun Feb 11 13:05:06 JST 2018
     */
    public void setWeatherNo(Integer weatherNo) {
        this.weatherNo = weatherNo;
    }
}