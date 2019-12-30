package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Test
    public void whenAskExitThenBye() throws IOException {
        this.testServer("exit",
                Joiner.on(System.lineSeparator()).join(
                        "wait command...",
                        "",
                        "bye",
                        "",
                        ""
                ));
    }

    @Test
    public void whenAskWrongThenWrong() throws IOException {
        this.testServer(
                Joiner.on(System.lineSeparator()).join(
                        "fghfdhtrfht",
                        "exit"),
                Joiner.on(System.lineSeparator()).join(
                        "wait command...",
                        "",
                        "wrong request",
                        "",
                        "bye",
                        "",
                        ""
                ));
    }

    @Test
    public void whenAskHelloThenGreetings() throws IOException {
        this.testServer(
                Joiner.on(System.lineSeparator()).join(
                        "hello",
                        "exit"),
                Joiner.on(System.lineSeparator()).join(
                        "wait command...",
                        "",
                        "Hello, dear friend, I'm a oracle.",
                        "",
                        "bye",
                        "",
                        ""
                ));
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }
}
