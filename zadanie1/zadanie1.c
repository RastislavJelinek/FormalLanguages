#include <stdio.h>
#include <unistd.h>
#include "zadanie1.h"
#include "linkList.h"

node_t *matches = NULL;
int amount = 0;
char* inputString = NULL;
int position = 0;
int bufferCounter = 0;
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
    inputString = input;
    matches = NULL;
    amount = 0;
    position = 0;

    char buffer;
    int matchStartPosition = position;

    while ((buffer = read_char()) > 0){
        bool result = false;
        result = q0(buffer);

        if (result != true) {
            check = true;
            ++matchStartPosition;
            position = matchStartPosition;
            continue;
        }

        ++amount;
        matches = add_node(matches, matchStartPosition);
        bufferCounter = 0;
        resetTemporary();
        matchStartPosition = position;
    }
}


char read_char() {
    char buffer = 0;
    if (inputString != NULL) {
        buffer = inputString[position];
        ++position;
        return buffer;
    }

    if (check == true && temporary[bufferCounter] != '\0') {
        buffer = temporary[bufferCounter];
        ++bufferCounter;
        return buffer;
    }
    if (read(0, &buffer, 1) <= 0)return 0;
    if (buffer == '\0' || buffer == '\n' || buffer == EOF) return 0;

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
    return read_char() == 'b' ? q7() : false;
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
    return read_char() == 'a' ? q4() : false;
}