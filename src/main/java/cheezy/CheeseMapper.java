package cheezy;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import static cheezy.CheeseData.parseDouble;

public class CheeseMapper extends Mapper<Object, Text, Text, Text> {

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        Cheese cheese = CheeseData.parseCheese(line);

        System.out.println(line);
        System.out.println(cheese.getCategoryTypeEn());
        System.out.println("-----");

        context.write(
                new Text(cheese.getManufacturerProvCode() + "," + cheese.getCategoryTypeEn() + "," + cheese.getMilkTypeEn()),
                new Text(cheese.getMoisturePercent() + "," + (cheese.isOrganic() ? "1" : "0")));
    }
}
