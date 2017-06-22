package com.jusfoun.utils

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**

 * Create on 2017-05-26.

 * @author Jeffer Lau <jefferlzu></jefferlzu>@gmail.com>
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class YamlTest {

    @Test
    fun readProperties() {
        val yamlProp = YamlProp()
        yamlProp.readProperties()
    }

}
