#include <stdio.h>
#include "zadanie1.h"
#include "linkList.h"

int main(void) {
    start();
    node_t* a = get_matches();
    
    //printf("Amount of matches: %d\n",amount);
    printList(a);
    free_list(a);
    return 0;
}