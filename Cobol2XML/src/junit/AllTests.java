package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LitTokenTest.class, NumTest.class, Parsertest.class, SymbolTest.class, TokenTest.class, CommentTest.class, DataTypetest.class, RegressionTest.class})
public class AllTests {

}
