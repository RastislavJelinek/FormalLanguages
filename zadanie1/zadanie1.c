#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include "zadanie1.h"
#include "linkList.h"

bool start(){
    int amount = 0;
    int position = -1;
    int result;
    char buffer;
    node_t *matches = NULL;
    while ((buffer = read_char()) > 0){
        switch(buffer){
            case 'a':
                        result = true;
                        break;
            case 'b':
                        result = q2();
                        break;
            case 'c':
                        result = q3();
                        break;
        }
        ++position; 
        if(result == true){
            ++amount;
            matches = add_node(matches, position);
        }
    }
    printf("Amount of matches: %d\n",amount);
    printList(matches);
    free_list(matches);
    return amount > 0 ? true : false;
}


char read_char(){
    char buffer;
    if(read(1, &buffer, 1) <= 0) return 0;
    if(buffer == '\n') return 0;
    return buffer;
}

bool q2(){
    switch(read_char()){
        case 'b':
                    return q4();
        case 'c':
                    return q5();
        default:    
                    return false;
    }
} 

bool q3(){
    return read_char() == 'a' ? true : false;
} 
bool q4(){
    switch(read_char()){
        case 'a':
                    return q6();
        case 'b':
                    return q5();
        default:    
                    return false;
    }
} 
bool q5(){
    return read_char() == 'a' ? q6() : false;
} 
bool q6(){
    return read_char() == 'b' ? true : false;
} 