package gol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main{

    public static void main(String[] args) {
        
        int gridWidth = 0, gridHeight = 0, startX = 0, startY = 0;
        boolean readData = false;
        char[] data;
        char[][] copyGrid = null;


        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String input = null;

            do{
                input = br.readLine();
                String[] word = input.trim().split(" ");
                
                if (input.charAt(0) == '#') continue;
    
                switch(word[0]){
                    
                    case "GRID":{
                        gridWidth = Integer.parseInt(word[1]);
                        gridHeight = Integer.parseInt(word[2]);
                        System.out.printf("GRID %d %d\n",gridWidth, gridHeight); // testing
                        break;
                    }
    
                    case "START":{
                        startX = Integer.parseInt(word[1]);
                        startY = Integer.parseInt(word[2]);
                        System.out.printf("START %d %d\n",startX, startY);// testing
                        break;
                    }
    
                    case "DATA":{
                        readData = true;
                        System.out.println("DATA");// testing
                        break;
                    }

                    default: break;
                    
                }

            }while ((null != input) && (!readData));

            Grid grid = new Grid(gridWidth, gridHeight, startX, startY);
            grid.printGrid(); // testing

            // start reading data
            int dataRow = 0;
   
            while( null != (input = br.readLine())) {

                System.out.println(input); // testing
                data = input.toCharArray();
                grid.setGrid(dataRow, data);
                grid.printGrid(); // testing
                dataRow++;

            }

            copyGrid = grid.getGrid();
            System.out.println("First grid");
            grid.printGrid(); // print the first grid

            // System.out.printf("this is \\u0000 [%c]", 0); // testing
            // System.out.printf("this is \\u0020 [%c]", 32); // testing

        }catch(IOException e){
            e.printStackTrace();
        }

        // start 5 iteration
        Grid nextGrid = new Grid(gridWidth, gridHeight, startX, startY, copyGrid);
        
        for (int y=0; y < gridHeight; y++) {

            for (int x=0; x < gridWidth; x++) {

                //boolean cellState = false;              // dead cell =  false; living cell = true 
                int neighbour = 0;


                if (copyGrid[y][x] != 0 && copyGrid[y][x] != 32){  // if not equal null or space,it is a living cell
                    


                }else if(copyGrid[y][x] == 0 || copyGrid[y][x] == 32){  // if cell is dead
                    

                }

            }
        }

    }
}