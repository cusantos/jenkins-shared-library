import ph.com.globe.isg.sdc.devops.ScriptTest

def call() {
    def scriptTest = new ScriptTest()
    scriptTest.stuff()
}

void get(body) {
    // pass in params body and ensure proper config of type map
    Map config = new utils().paramsConverter(body)

    Map response = new rest().request(config.url, config.headers, config.body, 'get')

    print response
}
