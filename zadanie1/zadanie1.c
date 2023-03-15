#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include "zadanie1.h"
#include "linkList.h"

node_t *matches = NULL;
int amount = 0;
char* string;
int position = 0;

node_t *get_matches() {
    return matches;
}
int getAmount(){
    return amount;
}

void start(char* input){
    string = input;
    matches = NULL;
    amount = 0;
    position = 0;


    int result;
    char buffer;
    int matchStartPosition = position;
    while ((buffer = read_char()) > 0){
        switch(buffer){
            case 'a':
                        result =  true;
                        break;
            case 'b':
                        result = q2();
                        break;
            case 'c':
                        result = q3();
                        break;
            default:    
                        result = false;
                        break;
        }
        if(result == true){
            ++amount;
            matches = add_node(matches, matchStartPosition);
        }
        matchStartPosition = position;
    }
}


char read_char(){
    char buffer;
    if(string != NULL){
        buffer = string[position];
    }else if(read(1, &buffer, 1) <= 0) return 0;
    if(buffer == '\0' || buffer == '\n' || buffer == EOF) return 0;
    ++position; 
    return buffer;
}

bool q2(){
    switch(read_char()){
        case 'a':
                    return q6();
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