package io.sample.mnms.irritatedtown.domain.transformations;

/**
 * Define a service object which is able to transform a type to another.
 *
 * @param <I> the input type
 * @param <O> the output type
 */
public interface Transformer<I, O> {

  /**
   * Apply the transformation for a given {@code input}.
   *
   * @param input the input to transform
   *
   * @return the result of the transformation
   */
  O transform(I input);
}
