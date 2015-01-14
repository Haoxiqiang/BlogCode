package info.haoxiqiang.third;

import java.util.function.Function;

import javax.swing.text.View;

/**
 * @author MikeW
 */
public class Main {

  public static void main(String[] args) {
  
    NameTestOld.main(args);
    NameTestNew.main(args);

    Listener listener = new Listener();
    String desc = 
    		listener.response( l->false);
    
    System.out.println("desc is " + desc);
    
  }
  
  @FunctionalInterface
  public interface OnClickListener{
	  public void onClick(View v);
  }
  
  static class Listener {
	  
	  
	  public String response(Function<Listener,Boolean> f){
		  String desc = "";
		  if(f.apply(this)){
			  desc = "f-flag is " + true;
		  }else{
			  desc = "f-flag is " + false;
		  }
		  return desc;
	  }
  }
}
