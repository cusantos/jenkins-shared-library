package ph.com.globe.isg.sdc.devops.util

/**
 * @author butch* Created on 14/06/2020.
 */

@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.2')
import groovyx.net.http.RESTClient

Map request(String url, Map headers = [:], Map body = [:], String method = 'get') {
    // initialize client
    def client = new RESTClient(url)

    // invoke helper request method depending upon interaction method
    switch(method) {
        case 'get':
            response = client.get(headers: headers, body: body)
            break
        case 'post':
            response = client.post(headers: headers, body: body)
            break
        case 'put':
            response = client.put(headers: headers, body: body)
            break
        default:
            throw new Exception("Invalid REST API interaction method ${method} specified.")
    }

    // handle the response
    assert response.status == 200 : "Invalid response status code from the REST API: ${response.status}."
    // return the data as a list instance with map interface
    return response['reader']
}


// converts closure body to config map, or returns same config map
// bridges gap between users of older DSL and newer DSL
Map paramsConverter(body) {
    // initialize config
    Map config = [:]

    // if we received older DSL, convert it into config map
    if (body instanceof Closure) {
        // evaluate the body block and collect configuration into the object
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
    }
    // if newer DSL, return same map as the config
    else if (body instanceof Map) {
        config = body
    }
    // params are invalid type
    else {
        throw new Exception('The parameter inputs are an invalid type. They must either be a Closure or Map. Consult the documentation for more information.')
    }

    return config
}
