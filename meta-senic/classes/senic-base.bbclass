python () {
    # setup context once, globally
    global senic_settings
    senic_settings = get_senic_settings(d)
    global datastore
    datastore = d
}


def render_template(infile, context=None, outfile=None):
    infile = datastore.expand(infile)
    template = u''
    # default is to overwrite the template
    # with the rendered version in-place
    if outfile is None:
        outfile = infile
    if context is None:
        context = senic_settings
    with open(infile, 'r') as infile_f:
        template = ''.join(infile_f.readlines())
    with open(outfile, 'w') as outfile_f:
        outfile_f.write(template.format(**context))

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
  


