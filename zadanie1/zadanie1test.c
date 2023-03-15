#include <stdio.h>
#include <unistd.h>
#include "greatest.h"
#include "zadanie1.h"



//test that should not met the regex
TEST empty_should_equal_zero() {
    start("");
    ASSERT_EQ(0, getAmount());
    PASS();
}
TEST not_regex_char_should_equal_zero() {
    start("kkhgijjfg");
    ASSERT_EQ(0, getAmount());
    PASS();
}

TEST c_should_equal_zero() {
    start("c");
    ASSERT_EQ(0, getAmount());
    PASS();
}
TEST b_should_equal_zero() {
    start("b");
    ASSERT_EQ(0, getAmount());
    PASS();
}

TEST bc_should_equal_zero() {
    start("bc");
    ASSERT_EQ(0, getAmount());
    PASS();
}

TEST bca_should_equal_zero() {
    start("bca");
    ASSERT_EQ(0, getAmount());
    PASS();
}


//test that should met the regex as whole
TEST a_should_equal_one() {
    start("a");
    ASSERT_EQ(1, getAmount());
    PASS();
}

TEST ca_should_equal_one() {
    start("ca");
    ASSERT_EQ(1, getAmount());
    PASS();
}

TEST cca_should_equal_one() {
    start("cca");
    ASSERT_EQ(1, getAmount());
    PASS();
}


TEST bab_should_equal_one() {
    start("bab");
    ASSERT_EQ(1, getAmount());
    PASS();
}


TEST bcab_should_equal_one() {
    start("bcab");
    ASSERT_EQ(1, getAmount());
    PASS();
}

TEST bbab_should_equal_one() {
    start("bbab");
    ASSERT_EQ(1, getAmount());
    PASS();
}

TEST bbbab_should_equal_one() {
    start("bbbab");
    ASSERT_EQ(1, getAmount());
    PASS();
}





//test that should contain regex, but not be a regex itself

TEST caa_should_equal_two() {
    start("caa");
    ASSERT_EQ(2, getAmount());
    PASS();
}


TEST random_string_with_two_regex_should_equal_two() {
    start("jiojhifbcabgh fojbghuaifh uhijgf");
    ASSERT_EQ(2, getAmount());
    PASS();
}

TEST regex_folowed_by_regex_should_equal_two() {
    start("bbbaba");
    ASSERT_EQ(2, getAmount());
    PASS();
}





SUITE(the_suite) {
    
    //test that should not met the regex
    RUN_TEST(empty_should_equal_zero);
    RUN_TEST(not_regex_char_should_equal_zero);
    RUN_TEST(c_should_equal_zero);
    RUN_TEST(b_should_equal_zero);
    RUN_TEST(bc_should_equal_zero);
    RUN_TEST(bca_should_equal_zero);

    //test that should met the regex as whole
    RUN_TEST(a_should_equal_one);
    RUN_TEST(ca_should_equal_one);
    RUN_TEST(cca_should_equal_one);
    RUN_TEST(bab_should_equal_one);
    RUN_TEST(bcab_should_equal_one);
    RUN_TEST(bbab_should_equal_one);
    RUN_TEST(bbbab_should_equal_one);

    //test that should contain regex, but not be a regex itself
    RUN_TEST(caa_should_equal_two);
    RUN_TEST(random_string_with_two_regex_should_equal_two);
    RUN_TEST(regex_folowed_by_regex_should_equal_two);
}



GREATEST_MAIN_DEFS();

int main(int argc, char **argv){
    GREATEST_MAIN_BEGIN();     
    RUN_SUITE(the_suite);
    GREATEST_MAIN_END(); 
}