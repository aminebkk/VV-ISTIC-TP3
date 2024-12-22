package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {

    @Mock
    private SSLSocket socket = Mockito.mock(SSLSocket.class);

    // This test will fail due to the fail() call.
    @Test
    public void preparedSocket_NullProtocols()  {

        TLSSocketFactory f = new TLSSocketFactory();

        Mockito.doAnswer(fail()).when(socket).setEnabledProtocols(Mockito.any());

        f.prepareSocket(socket);

        Mockito.verify(socket, Mockito.atLeastOnce()).getEnabledProtocols();
        Mockito.verify(socket, Mockito.atLeastOnce()).getSupportedProtocols();
        Mockito.verify(socket, Mockito.atLeastOnce()).setEnabledProtocols(Mockito.any());
    }

    @Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();

        f.prepareSocket(socket);

        Mockito
                .when(socket.getSupportedProtocols())
                .thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));

        Mockito
                .when(socket.getEnabledProtocols())
                .thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        Mockito
                .doAnswer(invoc -> {
                    var protocols = invoc.getArgument(0);
                    assertArrayEquals(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"}, (String[]) protocols);
                    return null;
                })
                .when(socket)
                .setEnabledProtocols(Mockito.any(String[].class));

        f.prepareSocket(socket);

        Mockito.verify(socket, Mockito.atLeastOnce()).getSupportedProtocols();
        Mockito.verify(socket, Mockito.atLeastOnce()).getEnabledProtocols();
        Mockito.verify(socket, Mockito.atLeastOnce()).setEnabledProtocols(Mockito.any());
    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}