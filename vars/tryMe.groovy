import ph.com.globe.isg.sdc.devops.ScriptTest

import ph.com.globe.isg.sdc.devops.util.rest

def call() {
    def scriptTest = new ScriptTest()
    scriptTest.stuff()
}

void get(body) {
    // pass in params body and ensure proper config of type map
    Map config = new rest().paramsConverter(body)

    Map response = new rest().request(config.url, config.headers, config.body, 'get')

    print response
}
