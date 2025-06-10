package tests.Day22_xmlFiles;

public class C01_UsingXmlFile {

     /*
        The main goal of TestNG is
        to give testers more control
        and to allow them to run tests collectively based on the defined purpose.

        For example:

        For a smoke test, we need to run the following classes every day:
        day19 C04
        day20 C02, C03, C04
        day21 C03, C04

        For an end-to-end (E2E) test, run the day20 package.

        For a system test, run the following tests:
        the entire day19 package
        from day20: the invalidPasswordTest inside C04,
                    and C05
        day21: C02 and C04

        Since there can be many purposes and group executions,
        without breaking the current structure,
        we need an additional mechanism
        that can run the packages/classes/methods we want
        for each specific purpose.

        TestNG provides the capability to run specified test methods
        using different methods:
        1- By going to the desired method and clicking the run button
        2- By preparing a special .xml file
           in the standards defined by TestNG,
           and specifying the test method, class, or packages we want to run
        3- By writing a special command in the terminal
     */
}
