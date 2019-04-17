import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class Temperature {

    private double temperature;

    public Temperature(){
        temperature = Constants.INITIAL_TEMPERATURE;
    }

    public void resetTemperature(){
        temperature = Constants.INITIAL_TEMPERATURE;
    }

    public double getTemperature(){
        return temperature;
    }

    public void decreaseTemperature(){
        temperature*=Constants.TEMPERATURE_SCHEDULE_MULTIPLIER;
    }

    public String toString(){
        NumberFormat numberFormat = new NumberFormat() {
            @Override
            public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
                return null;
            }

            @Override
            public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
                return null;
            }

            @Override
            public Number parse(String source, ParsePosition parsePosition) {
                return null;
            }
        };

        return "Temperature T = "+(new DecimalFormat("#0.000").format(temperature))+"K";
    }
}
