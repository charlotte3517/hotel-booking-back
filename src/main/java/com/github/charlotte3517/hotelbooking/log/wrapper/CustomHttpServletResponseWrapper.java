package com.github.charlotte3517.hotelbooking.log.wrapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private ServletOutputStream outputStream;
    private PrintWriter writer;
    private ServletOutputStreamCopier copier;
    private boolean responseCommitted = false;
    private String charset = "UTF-8";

    public CustomHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        response.setContentType("text/plain; charset=" + charset);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writer != null) {
            throw new IllegalStateException("getWriter() has already been called on this response.");
        }

        if (outputStream == null) {
            outputStream = getResponse().getOutputStream();
            copier = new ServletOutputStreamCopier(outputStream);
        }

        return copier;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (outputStream != null) {
            throw new IllegalStateException("getOutputStream() has already been called on this response.");
        }

        if (writer == null) {
            copier = new ServletOutputStreamCopier(getResponse().getOutputStream());
            writer = new PrintWriter(new OutputStreamWriter(copier, getResponse().getCharacterEncoding()), true);
        }

        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (!responseCommitted) {
            if (writer != null) {
                writer.flush();
            } else if (outputStream != null) {
                copier.flush();
            }
            responseCommitted = true;
        }
    }

    public byte[] getCopy() {
        if (copier != null) {
            return copier.getCopy();
        } else {
            return new byte[0];
        }
    }

    public String getBodyStr() throws IOException {
        writeDownBody(); // 確保寫入
        byte[] copy = getCopy();
        return new String(copy, charset);
    }

    public void writeDownBody() throws IOException {
        if (copier == null) {
            if (outputStream == null) {
                getOutputStream();
            }
        }
        if (!responseCommitted && copier != null) {
            responseCommitted = true;
        }
    }

    public boolean isResponseCommitted() {
        return responseCommitted;
    }

    public Map<String, String> getResponseHeaders() {
        Map<String, String> headers = new HashMap<>();
        for (String headerName : getHeaderNames()) {
            headers.put(headerName, getHeader(headerName));
        }
        return headers;
    }

    class ServletOutputStreamCopier extends ServletOutputStream {

        private OutputStream outputStream;
        private ByteArrayOutputStream copy;

        public ServletOutputStreamCopier(OutputStream outputStream) {
            this.outputStream = outputStream;
            this.copy = new ByteArrayOutputStream();
        }

        @Override
        public void write(int b) throws IOException {
            copy.write(b);
            outputStream.write(b);
        }

        @Override
        public void flush() throws IOException {
            outputStream.flush();
            copy.flush();
        }

        public byte[] getCopy() {
            return copy.toByteArray();
        }

        public void writeDownBody() throws IOException {
            outputStream.write(getCopy());
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
        }
    }
}
