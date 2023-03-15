#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include "zadanie1.h"
#include "linkList.h"

node_t *matches = NULL;
int amount = 0;
char* string;
int position = 0;
int temporaryCounter = 0;
char temporary[20];
bool check = false; 

node_t *get_matches() {
    return matches;
}
int getAmount(){
    return amount;
}

void resetTemporary(){
    for(int i = 0; i < 10; ++i){
        temporary[i] = '\0';
    }
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
        result = q0(buffer);

        if(result == true){
            ++amount;
            matches = add_node(matches, matchStartPosition);
        
        } else if (check == true) {
            // go back to the position where we started checking for the current match
            position = matchStartPosition + 1;
            // reset the temporary buffer
            resetTemporary();
            // try the next character
            continue;
        }

        result = false;
        check = false;
        temporaryCounter = 0;
        resetTemporary();
        matchStartPosition = position;
    }
}


char read_char() {
    char buffer;
    if (temporary[temporaryCounter] != '\0' && check == true) {
        buffer = temporary[temporaryCounter];
        ++temporaryCounter;
        return buffer;
    } else if (string != NULL) {
        buffer = string[position];
    } else if (read(1, &buffer, 1) <= 0) {
        return 0;
    }

    if (buffer == '\0' || buffer == '\n' || buffer == EOF) {
        return 0;
    }

    temporary[temporaryCounter] = buffer;
    ++temporaryCounter;
    ++position;

    return buffer;
}






bool q0(char input){
    switch(input){
        case 'a':
                    return q1();
        case 'b':
                    return q2();
        case 'c':
                    return q3();
        default:    
                    return false;
    }
}
bool q1(){
    return true;
}

bool q2(){
    switch(read_char()){
        case 'a':
                    return q4();
        case 'b':
                    return q5();
        case 'c':
                    return q6();
        default:    
                    return false;
    }
} 




bool q3(){
    return read_char() == 'a' ? q1() : false;
} 
bool q4(){
    switch(read_char()){
        case 'b':
                    return q7();
        default:    
                    return false;
    }
} 
bool q5(){
    switch(read_char()){
        case 'a':
                    return q4();
        case 'b':
                    return q8();
        default:    
                    return false;
    }
} 
bool q6(){
    switch(read_char()){
        case 'a':
                    return q4();
        case 'c':
                    return q6();
        default:    
                    return false;
    }
} 

bool q7(){
    return true;
}

bool q8(){
    switch(read_char()){
        case 'a':
                    return q4();
        default:    
                    return false;
    }
}