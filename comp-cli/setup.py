#!python3
#
# gitlab project demo
#
# Copyright (C) 2021 Olivier Korach
# mailto:olivier.korach AT gmail DOT com
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 3 of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with this program; if not, write to the Free Software Foundation,
# Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
#
import setuptools
import src.version as version


with open("README.md", "r") as fh:
    long_description = fh.read()
setuptools.setup(
    name='person',
    version=version.PACKAGE_VERSION,
    scripts=['demo-mr'],
    author="Olivier Korach",
    author_email="olivier.korach@gmail.com",
    description="A demo monorepo GitLab project with GitLab CI and SonarQube scanner integration",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="https://gitlab.com/okorach/mr-demo",
    project_urls={
        "Bug Tracker": "https://github.com/okorach/mr-demo/-/issues",
        "Documentation": "https://github.com/okorach/mr-demo/README.md",
        "Source Code": "https://github.com/okorach/mr-demo",
    },
    packages=setuptools.find_packages(),
    package_data={
        "src": ["LICENSE"]
    },
    install_requires=[
        'argparse'
    ],
    classifiers=[
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: GNU Lesser General Public License v3 or later (LGPLv3+)",
        "Operating System :: OS Independent",
    ],
    entry_points={
        'console_scripts': [
            'person = src.person:main'
        ]
    },
    python_requires='>=3.6',
)
