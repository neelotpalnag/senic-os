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
    """ Renders the given Jinja template with the given context
    to the given output file.
    By default the context consists of all variables defined in local.conf
    that begin with `SNC_`.
    If no outputfile is given, the template is overwritten with the rendered
    version. This can be useful since mostly the template has already bee copied
    by bitbake into the working directory.
    The usual pattern is then to perform the rendering inside `do_compile` and then
    `do_install` simply moves them to the desired final location."""

    template = jinja_env.get_template(name)
    # default is to overwrite the template
    # with the rendered version in-place
    if outfile is None:
        outfile = template.filename
    if context is None:
        context = senic_settings
    with open(outfile, 'w') as outfile_f:
        outfile_f.write(template.render(**context))


def get_senic_settings(d):
    result = dict()
    for key in d.keys():
      if key.startswith('SNC'):
        result[key] = (d.getVar(key, True))
    return result

