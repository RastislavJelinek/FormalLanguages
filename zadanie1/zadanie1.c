#include  <stdio.h>
#include <unistd.h>
#include <stdbool.h>
#include "zadanie1.h"

int main(void) {
    int amount = 0;
    int result;
    char buffer;
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
            default: 
                        continue;
        }
        if(result == 1){
            ++amount;
        }
    }
    printf("%d\n",amount);

return 0;
}

char read_char(){
    char buffer;
    if(read(0, &buffer, 1) <= 0) return 0;
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