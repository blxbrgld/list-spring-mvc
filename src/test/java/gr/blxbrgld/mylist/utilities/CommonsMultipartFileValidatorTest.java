package gr.blxbrgld.mylist.utilities;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.junit.Test;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author blxbrgld
 */
public class CommonsMultipartFileValidatorTest {

    @SuppressWarnings({"unchecked", "rawtypes"})
    private CommonsMultipartFileValid commonsMultipartFileValid = new CommonsMultipartFileValid() {
        @Override
        public String fileSize() {
            return "2000000"; //2 MB
        }

        @Override
        public String[] supportedFileTypes() {
            return new String[] { "jpg", "jpeg", "png" };
        }

        @Override
        public String message() {
            return "Validation Error Message";
        }

        @Override
        public Class[] groups() {
            return null;
        }

        @Override
        public Class[] payload() {
            return null;
        }

        @Override
        public Class annotationType() {
            return null;
        }
    };

    @Test
    public void validateCommonsMultipartValidator() {
        CommonsMultipartFileValidator validator = new CommonsMultipartFileValidator();
        validator.initialize(commonsMultipartFileValid);
        assertThat(validator.isValid(new CommonsMultipartFile(new ValidFileItem()), null), is(true));
        assertThat(validator.isValid(new CommonsMultipartFile(new InvalidFileName()), null), is(false));
        assertThat(validator.isValid(new CommonsMultipartFile(new InvalidFileSize()), null), is(false));
    }

    /**
     * Valid File Item Implementation
     */
    private class ValidFileItem implements FileItem {

        private static final long serialVersionUID = 1L;

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public String getName() {
            return "name.jpg"; //A Valid Filename
        }

        @Override
        public boolean isInMemory() {
            return false;
        }

        @Override
        public long getSize() {
            return 500;
        }

        @Override
        public byte[] get() {
            return new byte[0];
        }

        @Override
        public String getString(String encoding) throws UnsupportedEncodingException {
            return null;
        }

        @Override
        public String getString() {
            return null;
        }

        @Override
        public void write(File file) throws Exception {

        }

        @Override
        public void delete() {

        }

        @Override
        public String getFieldName() {
            return null;
        }

        @Override
        public void setFieldName(String s) {

        }

        @Override
        public boolean isFormField() {
            return false;
        }

        @Override
        public void setFormField(boolean b) {

        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return null;
        }

        @Override
        public FileItemHeaders getHeaders() {
            return null;
        }

        @Override
        public void setHeaders(FileItemHeaders fileItemHeaders) {

        }
    }

    /**
     * Invalid Filename Item Implementation
     */
    private class InvalidFileName extends ValidFileItem {

        private static final long serialVersionUID = 1L;

        @Override
        public String getName() {
            return "name.pdf";
        }
    }

    /**
     * Invalid Filesize Item Implementation
     */
    private class InvalidFileSize extends ValidFileItem {

        private static final long serialVersionUID = 1L;

        @Override
        public long getSize() {
            return 3000000; //3 MB
        }
    }
}
