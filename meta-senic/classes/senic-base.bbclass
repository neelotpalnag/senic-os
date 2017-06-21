python () {
    # setup context once, globally
    global senic_settings
    senic_settings = get_senic_settings(d)
    global datastore
    datastore = d
    from jinja2 import Environment, FileSystemLoader
    global jinja_env
    jinja_env = Environment(loader=FileSystemLoader(d.expand('${WORKDIR}')))
}


def render_template(name, context=None, outfile=None):
    template = jinja_env.get_template(name)
    # default is to overwrite the template
    # with the rendered version in-place
    if outfile is None:
        outfile = template.filename
    if context is None:
        context = senic_settings
    with open(outfile, 'w') as outfile_f:
        outfile_f.write(template.render(**context))


def debug(value):
    import json
    with open('/tmp/debugx', 'w') as outfile_f:
        outfile_f.write(json.dumps(value))

def get_senic_settings(d):
    result = dict()
    for key in d.keys():
      if key.startswith('SNC'):
        result[key] = (d.getVar(key, True))
    return result

