package gol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main{

    public static void main(String[] args) {
        
        int gridWidth = 0, gridHeight = 0, startX = 0, startY = 0;
        boolean readData = false;
        char[] data;
        char[][] currentGridStatus = null;
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
                        break;
                    }
    
                    case "START":{
                        startX = Integer.parseInt(word[1]);
                        startY = Integer.parseInt(word[2]);
                        break;
                    }
    
                    case "DATA":{
                        readData = true;
                        break;
                    }

                    default: break;
                    
                }

            }while ((null != input) && (!readData));

            Grid grid = new Grid(gridWidth, gridHeight, startX, startY);

            // start reading data
            int dataRow = 0;
   
            while( null != (input = br.readLine())) {

                data = input.toCharArray();
                grid.initializeGrid(dataRow, data);
                dataRow++;

            }

            currentGridStatus = grid.getGrid(); 

            System.out.println("Initial grid");
            grid.printGrid(); // print the first grid

        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        // start 5 iteration

        for (int gen = 1; gen < 6; gen++) {

            copyGrid = new char[gridHeight][gridWidth];
            for (int i=0; i < gridHeight; i++){

                for (int j=0; j < gridWidth; j++) {

                    copyGrid[i][j] = currentGridStatus[i][j];
                }
            }

            Grid nextGrid = new Grid(gridWidth, gridHeight, startX, startY, copyGrid);

            // check every cell in the grid for #neighbour
            for (int y=0; y < gridHeight; y++) { 

                for (int x=0; x < gridWidth; x++) {

                    int neighbour = 0;
                    int deadNeighbour = 0;       // for debug

                    for (int neighbourRow = y-1; neighbourRow <= y+1; neighbourRow++){

                        if (neighbourRow < 0) continue;
                        if (neighbourRow == gridHeight) continue;

                        //System.out.printf("1) in the loop to check neighbourRow: %d\n",neighbourRow); // testing

                        for (int neighbourCol = x-1; neighbourCol <= x+1; neighbourCol++){

                            if (neighbourCol < 0) continue;
                            if (neighbourCol == gridWidth) continue;
                            if (neighbourRow == y && neighbourCol == x) continue;  // skip checking own status as neighbour
                            
                            if (currentGridStatus[neighbourRow][neighbourCol] == '*'){

                                //System.out.printf("2) in the loop to check (living) neighbourCol: %d\n",neighbourCol); // testing
                                neighbour++;
                                
                            }
                            else if (currentGridStatus[neighbourRow][neighbourCol] == 0 || currentGridStatus[neighbourRow][neighbourCol] == 32){

                                //System.out.printf("2) in the loop to check (dead) neighbourCol: %d\n",neighbourCol); // testing
                                deadNeighbour++; // for debug only
                                
                            }else{
                                System.out.printf("2) Neighbour cell contain char : %c\n",currentGridStatus[neighbourRow][neighbourCol]); // for debug
                            }
                            
                        }
                    }

                    //System.out.printf("3) currentgrid[%d][%d] has %d live neighbour and %d dead neighbour\n",y,x,neighbour,deadNeighbour);  // testing

                    if (currentGridStatus[y][x] == '*'){  // if myself contain *, it a living cell
                        
                        if (neighbour<=1 || neighbour>=4){
                            nextGrid.setGrid(y,x,' '); 
                        }
               
                    }else if(currentGridStatus[y][x] == 0 || currentGridStatus[y][x] == 32){  // if myself is a dead cell
                        
                        if (neighbour==3) {
                            nextGrid.setGrid(y, x, '*');
                            //System.out.printf("4) dead cell has 3 neighbour, it becomes alive, cell will be %c\n",nextGrid.getGrid()[y][x]); // testing
                        }

                    }else{
                        System.out.printf("4) Myself contain char : %c\n",currentGridStatus[y][x]);  // for debug, if cell dont contain null/space/*
                    }


                }
            }

            System.out.printf("Generation %d\n",gen);
            nextGrid.printGrid(); // print result of first iteration

            //System.out.printf("currentGridStatus is %s\n",Arrays.deepToString(currentGridStatus)); // testing

            // update curentGridStatus 
            for (int i=0; i < gridHeight; i++){

                for (int j=0; j < gridWidth; j++) {

                    currentGridStatus[i][j] = nextGrid.getGrid()[i][j];
                }
            } 

            //System.out.printf("updated currentGridStatus is %s\n",Arrays.deepToString(currentGridStatus)); // testing
            
        }
    }
}