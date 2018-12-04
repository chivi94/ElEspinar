package ivagonz.antroma.elespinar.pdf_asynctask;

import android.content.Context;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;
import java.io.IOException;

import de.number42.subsampling_pdf_decoder.PDFDecoder;
import de.number42.subsampling_pdf_decoder.PDFRegionDecoder;


/**
 * Created by ivgor on 21/03/2017.
 */

public class PDFPagerAdapter extends PagerAdapter {
    /**
     * context for the view
     */
    private Context context;

    /**
     * pdf file to show
     */
    private File file;

    /**
     * file descriptor of the PDF
     */
    private ParcelFileDescriptor mFileDescriptor;


    private float scale;

    /**
     * this renderer is only used to count the pages
     */
    private PdfRenderer renderer;

    /**
     * @param file the pdf file
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PDFPagerAdapter(Context context, File file) {
        super();
        this.context = context;
        this.file = file;
        this.scale = 8;
        try {
            mFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            renderer = new PdfRenderer(mFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Object instantiateItem(ViewGroup container, int position) {

        SubsamplingScaleImageView imageView = new SubsamplingScaleImageView(context);

        // the smaller this number, the smaller the chance to get an "outOfMemoryException"
        // still, values lower than 100 really do affect the quality of the pdf picture
        int minimumTileDpi = 120;
        imageView.setMinimumTileDpi(minimumTileDpi);

        //sets the PDFDecoder for the imageView
        imageView.setBitmapDecoderFactory(() -> new PDFDecoder(position, file, scale));

        //sets the PDFRegionDecoder for the imageView
        imageView.setRegionDecoderFactory(() -> new PDFRegionDecoder(position, file, scale));

        ImageSource source = ImageSource.uri(file.getAbsolutePath());

        imageView.setImage(source);

        container.addView(imageView);
        return imageView;
    }

    /**
     * gets the pdf site count
     *
     * @return pdf site count
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int getCount() {
        return renderer.getPageCount();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
