package capturing;

import mockit.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * just example for three mock annotations
 * Created by twer on 2017/5/31.
 */
public class FooTest {



    @Test
    public void fooSub_should_be_mocked_when_capturing_foo(final @Capturing Foo foo){

        final FooSub fooSub = new FooSub();
        new Expectations() {{
            foo.hello();result="mocked";
        }};

        assertThat("should return mocked",fooSub.hello(),is("mocked"));

    }

    @Test
    public void fooSub_should_not_be_mocked_when_mock_foo(final @Mocked Foo foo){

        final FooSub fooSub = new FooSub();
        new Expectations(){{
            foo.hello();result="mocked"; minTimes=0;
        }};

        assertThat(fooSub.hello(),is("FooSub"));
    }

    @Test
    public void fooSub_should_not_be_mocked_when_injectable_foo(final @Injectable Foo foo){

        final FooSub fooSub = new FooSub();

        new Expectations(){{
            foo.hello();result="mocked"; minTimes=0;
        }};

        assertThat(fooSub.hello(),is("FooSub"));
    }

    @Test
    public void other_foo_should_be_mocked_when_mock_a_foo(final @Mocked Foo foo){
        final Foo other = new Foo();

        new Expectations(){{
           foo.hello();result="mocked"; minTimes=0;
        }};

        assertThat(other.hello(),is("mocked"));
    }

    @Test
    public void other_foo_should_be_mocked_when_capturing_a_foo(final @Capturing Foo foo){
        final Foo other = new Foo();

        new Expectations(){{
            foo.hello();result="mocked"; minTimes=0;
        }};

        assertThat(other.hello(),is("mocked"));

    }

    @Test
    public void other_foo_should_not_be_mocked_when_injectable_a_foo(final @Injectable Foo foo){
        final Foo other = new Foo();

        new Expectations(){{
            foo.hello();result="mocked"; minTimes=0;
        }};

        assertThat(other.hello(),is("Foo"));
        System.out.println(other);
    }


    @Test
    public void should_be_distinct_instances_by_diff_constructor(final @Mocked Foo foo){

        new Expectations(){{
            final Foo foo1 = new Foo("1");
            final Foo foo2 = new Foo("2");
            foo1.hello();result="foo1";
            foo2.hello();result="foo2";
        }};

        assertThat("foo1",is(new Foo("1").hello()));
        assertThat("foo2",is(new Foo("2").hello()));
    }

    @Test
    public void should_be_distinct_instances_by_diff_constructor2(final @Mocked Foo foo1,final @Mocked Foo foo2){

        new Expectations(){{
            new Foo("1"); result=foo1;
            new Foo("2"); result=foo2;
            foo1.hello();result="foo1";
            foo2.hello();result="foo2";
        }};

        assertThat("foo1",is(new Foo("1").hello()));
        assertThat("foo2",is(new Foo("2").hello()));
    }


    @Test
    public void should_be_distinct_instances(final @Mocked Foo foo1,final @Mocked Foo foo2){

        new Expectations(){{
            foo1.hello();result="foo1";
            foo2.hello();result="foo2";
        }};


        assertThat("foo1",is(foo1.hello()));
        assertThat("foo2",is(foo2.hello()));
    }


}