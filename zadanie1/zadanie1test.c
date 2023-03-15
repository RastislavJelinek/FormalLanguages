#include <stdio.h>
#include <unistd.h>
#include "greatest.h"
#include "zadanie1.h"


TEST a_should_equal_true(void) {
    bool a = start();
    write(1, "a", 1);
    ASSERT_EQ(true, a);
    PASS();
}



SUITE(the_suite) {
    RUN_TEST(a_should_equal_true);
}



GREATEST_MAIN_DEFS();

int main(int argc, char **argv){
    GREATEST_MAIN_BEGIN();     
    RUN_SUITE(the_suite);
    GREATEST_MAIN_END(); 
}