package examples.simplelinearregression;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import java.util.Scanner;


public class SLRAgent extends Agent {

  protected void setup() {
    //System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());

  }
   
  private class MySLR{
    int[] sales = {4,8,12,16,20,24,28,32,36};
    int[] advertising = {2,4,6,8,10,12,14,16,18};
    final int n = 9;

    double xiyi;
    double xi2;
    double yi;
    double xi;

    
    public double obtener_xiyi(){
        xiyi = 0;
        for(int i=0; i<n; i++){
            xiyi = xiyi + (sales[i] * advertising[i]);
        }
        xiyi = n * xiyi;
        return xiyi;
    }
    
    public double obtener_xi2(){
        xi2 = 0;
        for(int i=0; i<n; i++){            
            xi2 = xi2 + (advertising[i] * advertising[i]);                        
        }
        xi2 = n * xi2;
        return xi2;
    }
    
    public double obtener_yi(){
        yi = 0;
        for (int i=0; i<n; i++){
            yi = yi + sales[i];
        }
        return yi;
    }
    
    public double obtener_xi(){
        xi = 0;
        for(int i=0; i<n; i++){
            xi = xi + advertising[i];
        }
        return xi;
    }
    
    public double beta_uno(){         
        double beta_1 = (obtener_xiyi()-(obtener_xi()*obtener_yi()))/(obtener_xi2()-(obtener_xi()*obtener_xi()));
        
        return beta_1;
    }
    
    public double beta_cero(){        
        double beta_0 = (obtener_yi()-(beta_uno()*obtener_xi()))/n;
        return beta_0;
    }
    
    public double calcular_y(double x){
        double resultado = beta_cero() + beta_uno() * x;
        return resultado;
    }
  }

  private class MyOneShotBehaviour extends OneShotBehaviour {    

    public void action() {
        MySLR obj = new MySLR();
        Scanner entrada = new Scanner(System.in);
        double x;
        Recta simple = new Recta();
        System.out.println("Valor de x: ");
        x = entrada.nextDouble();
        
        System.out.printf("y = %.3f + %.3f * %s\n", obj.beta_cero(), obj.beta_uno(), x);
        System.out.printf("y = %.3f\n", obj.calcular_y(x));
        
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}