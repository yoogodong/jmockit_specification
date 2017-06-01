package cascade;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by twer on 2017/6/1.
 */
public class CascadeMockTest {

    @Test
    public void mocked_A_should_return_empty_B_that_not_null_(final @Mocked A a){

        assertThat(a.b(),notNullValue());
    }

    @Test
    public void c_should_not_be_null(final @Mocked A a){

        assertThat(a.b().c(),instanceOf(C.class));
    }

    @Test
    public void cascade_object_should_be_mocked(@Mocked final A a){

        final C c = a.b().c();

        final String changed = "changed";

        new Expectations(){{
            c.id();result= changed;
        }};

        assertThat(a.b().c().id(),is(changed));
    }
}