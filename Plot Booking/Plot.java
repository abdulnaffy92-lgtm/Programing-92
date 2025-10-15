public class Plot{
String id;
PlotType type;
boolean available;
double price;

public Plot(String id, PlotType type, boolean available){
this.id = id;
this.type = type;
this.available = available;
this.price = setPriceByType();
}

public enum PlotType{
RES_5_MARLA,RES_10_MARLA,RES_1_CANAL,COMM_SHOP,COMM_OFFICE,PARKING
}


public boolean bookPlot(){
if(available){
available = false;
return true;
}else{
return true;
}
}
public double setPriceByType(){
switch(type){
case RES_5_MARLA:
return 4000000.00;
case RES_10_MARLA:
return 7500000.00;
case RES_1_CANAL:
return 14000000.00;
case COMM_SHOP:
return 3000000.00;
case COMM_OFFICE:
return 5000000.00;
case PARKING:
return 200000.00;
default:
return 0.00;


}

}
public boolean cancelBooking(){
if(!available){
available = true;
return true;
}else{
return false;
}
}
public String toString(){
return "Plot ID :" + id +"|  PlotType :"+ type +"| Price :" + price +"| Booked :" available + "| Shape of Plot :"shape; }
}