package io.sample.mnms.irritatedtown.domain.transformations;

/**
 * Transformer which is applying a SOAP body transformation to a JSON body transformation.
 *
 * <p>Both stream are represented as {@link String} so that we have a generic implementation which can fit with all our
 * needs. However, since we have to control the REST exposition, it may interesting to control information more
 * finely.</p>
 */
public class SoapToJsonTransformer implements Transformer<String, String> {
  @Override
  public String transform(final String input) {
    return input;
  }
}
