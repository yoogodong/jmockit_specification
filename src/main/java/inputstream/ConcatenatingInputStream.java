package inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by twer on 2017/5/23.
 */
public final class ConcatenatingInputStream extends InputStream
{
    private final Queue<InputStream> sequentialInputs;
    private InputStream currentInput;

    public ConcatenatingInputStream(InputStream... sequentialInputs)
    {
        this.sequentialInputs = new LinkedList<InputStream>(Arrays.asList(sequentialInputs));
        currentInput = this.sequentialInputs.poll();
    }

    @Override
    public int read() throws IOException
    {
        if (currentInput == null) return -1;

        int nextByte = currentInput.read();

        if (nextByte >= 0) {
            return nextByte;
        }

        currentInput = sequentialInputs.poll();
        return read();
    }
}
