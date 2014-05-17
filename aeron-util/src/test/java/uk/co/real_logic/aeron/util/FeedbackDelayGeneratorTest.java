/*
 * Copyright 2014 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.aeron.util;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class FeedbackDelayGeneratorTest
{
    public static final long MAX_BACKOFF = 60;
    public static final long GROUP_SIZE = 10;
    public static final long GRTT = 10;

    public FeedbackDelayGenerator generator = new FeedbackDelayGenerator(MAX_BACKOFF, GROUP_SIZE, GRTT);

    @Test
    public void shouldNotExceedTmaxBackoff()
    {
        IntStream.range(0, 100000).forEach((i) ->
        {
            final double delay = generator.generateNewDelay();
            assertThat(delay, lessThanOrEqualTo((double)MAX_BACKOFF));
        });
    }
}
