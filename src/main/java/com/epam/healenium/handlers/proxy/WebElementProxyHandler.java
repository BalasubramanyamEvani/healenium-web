/**
 * Healenium-web Copyright (C) 2019 EPAM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.epam.healenium.handlers.proxy;

import com.epam.healenium.SelfHealingEngine;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class WebElementProxyHandler extends BaseHandler {

    private final WebElement delegate;

    public WebElementProxyHandler(WebElement delegate, SelfHealingEngine engine) {
        super(engine);
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("findElement".equals(method.getName())) {
            log.debug("Caught findElement: invoking the healing version...");
            return findElement((By) args[0]);
        }
        if ("getWrappedElement".equals(method.getName())) {
            return delegate;
        }
        return method.invoke(delegate, args);
    }
}
